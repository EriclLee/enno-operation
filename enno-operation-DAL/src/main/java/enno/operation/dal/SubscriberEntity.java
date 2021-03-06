package enno.operation.dal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by sabermai on 2015/11/13.
 */
@Entity
@Table(name = "subscriber", schema = "", catalog = "enno_operationserverdb")
public class SubscriberEntity {
    private int id;
    private String name;
    private String address;
    private String comments;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer status;
    private Integer dataStatus;
    private Set<EventsourceEntity> eventsourceEntities;

    @ManyToMany(targetEntity = EventsourceEntity.class)
    @JoinTable(name = "eventsource_subscriber_map",
            joinColumns = @JoinColumn(name = "SubscriberId", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "EventsourceId", referencedColumnName = "Id")
    )
    public Set<EventsourceEntity> getEventsourceEntities() {
        return eventsourceEntities;
    }

    public void setEventsourceEntities(Set<EventsourceEntity> eventsourceEntities) {
        this.eventsourceEntities = eventsourceEntities;
    }

    @Id
    @Column(name = "Id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Address", nullable = true, insertable = true, updatable = true, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Comments", nullable = true, insertable = true, updatable = true, length = 200)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "CreateTime", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "UpdateTime", nullable = true, insertable = true, updatable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "Status", nullable = true, insertable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "DataStatus", nullable = true, insertable = true, updatable = true)
    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriberEntity that = (SubscriberEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (dataStatus != null ? !dataStatus.equals(that.dataStatus) : that.dataStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dataStatus != null ? dataStatus.hashCode() : 0);
        return result;
    }
}
