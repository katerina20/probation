package sigmasoftware.downloader.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "files")
public class FileData {

    @Id
    private Long id;
    private String name;
    private String url;

    @Column(name = "admin_name")
    private String adminName;
}
