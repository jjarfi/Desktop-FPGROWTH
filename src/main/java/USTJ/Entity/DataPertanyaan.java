package USTJ.Entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="t_pertanyaan")
public class DataPertanyaan {

    @Id
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String pertanyaan;
    private Timestamp createdate;
    private String lasupdate;
    private String id_admin;

}
