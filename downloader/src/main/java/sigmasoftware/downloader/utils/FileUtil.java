package sigmasoftware.downloader.utils;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.lang.String.format;

@ApplicationScoped
public class FileUtil {

    private final HttpClient httpClient;

    public FileUtil() {
        this.httpClient = HttpClient.newBuilder()
                                    .connectTimeout(Duration.ofSeconds(10))
                                    .build();
    }

    public byte[] download(final String uri) {
        int fileLength = (int) fileLength(uri);
        int chunkSize = 100000;
        int firstBytePos = 0;
        int lastBytePos = chunkSize - 1;

        byte[] downloadedBytes = new byte[fileLength];
        int downloadedLength = 0;

        while (downloadedLength < fileLength) {
            Response response = download(uri, firstBytePos, lastBytePos);

            try (response.inputStream) {
                byte[] chunkedBytes = response.inputStream.readAllBytes();
                downloadedLength += chunkedBytes.length;

                if (response.status == 206) {
                    System.arraycopy(chunkedBytes, 0, downloadedBytes, firstBytePos, chunkedBytes.length);
                    firstBytePos = lastBytePos + 1;
                    lastBytePos = Math.min(lastBytePos + chunkSize, fileLength - 1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return downloadedBytes;
    }

    private Response download(String uri, int firstBytePos, int lastBytePos) {
        HttpRequest request = Try
                .of(() -> HttpRequest.newBuilder(new URI(uri))
                                     .header("Range", format("bytes=%d-%d", firstBytePos, lastBytePos))
                                     .GET()
                                     .build())
                .get();

        HttpResponse<InputStream> response = Try
                .of(() -> httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream()))
                .get();
        return new Response(new BufferedInputStream(response.body()), response.statusCode(), response.headers());
    }

    private long fileLength(String uri) {
        HttpRequest headRequest = Try
                .of(() -> HttpRequest.newBuilder(new URI(uri))
                                     .method("HEAD", HttpRequest.BodyPublishers.noBody())
                                     .build())
                .get();
        HttpResponse<String> httpResponse = Try
                .of(() -> httpClient.send(headRequest, HttpResponse.BodyHandlers.ofString()))
                .get();
        return httpResponse.headers()
                           .firstValueAsLong("content-length")
                           .orElse(0L);
    }

    @AllArgsConstructor
    public static class Response {

        final BufferedInputStream inputStream;
        final int status;
        final HttpHeaders headers;
    }
}
