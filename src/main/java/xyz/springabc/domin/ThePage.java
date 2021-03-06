package xyz.springabc.domin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Page generated by hbm2java
 */
@Entity
@Table(name = "page", catalog = "spring_abc", uniqueConstraints = @UniqueConstraint(columnNames = "url") )
public class ThePage implements java.io.Serializable {

	private static final long serialVersionUID = -1463781496520664879L;
	private Integer id;
	@Size(min=3,max=30,message="链接三到三十个字符")
	private String url;
	@NotNull(message="标题不能为空")
	@Size(max=60,message="标题最多六十个字符")
	private String title;
	private Date createAt;
	private Date updateAt;
	@NotNull(message="内容不能为空")
	@Size(max=60000,message="内容最多六万个字符")
	private String content;
	private Long viewCount=1L;

	public ThePage() {
	}

	public ThePage(String url, String title, String content) {
		this.url = url;
		this.title = title;
		this.content = content;
	}

	public ThePage(String url, String title, Date createAt, Date updateAt, String content, Long viewCount) {
		this.url = url;
		this.title = title;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.content = content;
		this.viewCount = viewCount;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "url", unique = true, nullable = false)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_at", length = 19)
	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at", length = 19)
	public Date getUpdateAt() {
		return this.updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Column(name = "content", nullable = false, length = 16777215)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "view_count")
	public Long getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}

}
