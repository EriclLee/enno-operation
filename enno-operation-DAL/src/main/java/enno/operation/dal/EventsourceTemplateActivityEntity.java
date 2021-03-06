package enno.operation.dal;

import javax.persistence.*;

/**
 * Created by sabermai on 2015/11/13.
 */
@Entity
@Table(name = "eventsource_template_activity", schema = "", catalog = "enno_operationserverdb")
public class EventsourceTemplateActivityEntity {
    private int id;
    private String name;
    private String displayName;
    private String comments;
    private Integer allowNull;
    private EventsourceTemplateEntity eventsourceTemplate;

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
    @Column(name = "DisplayName", nullable = true, insertable = true, updatable = true, length = 45)
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
    @Column(name = "AllowNull", nullable = true, insertable = true, updatable = true)
    public Integer getAllowNull() {
        return allowNull;
    }

    public void setAllowNull(Integer allowNull) {
        this.allowNull = allowNull;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsourceTemplateActivityEntity that = (EventsourceTemplateActivityEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (displayName != null ? !displayName.equals(that.displayName) : that.displayName != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (allowNull != null ? !allowNull.equals(that.allowNull) : that.allowNull != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (allowNull != null ? allowNull.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "EventSourceTemplateId", referencedColumnName = "Id", nullable = false)
    public EventsourceTemplateEntity getEventsourceTemplate() {
        return eventsourceTemplate;
    }

    public void setEventsourceTemplate(EventsourceTemplateEntity eventsourceTemplate) {
        this.eventsourceTemplate = eventsourceTemplate;
    }
}
