package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/*
 * Named Queries for Entity Drawing
 */

@NamedQueries({
	@NamedQuery(name  = "Drawing.findDrawingById",
		        query =
		        	"SELECT d " +
		        	"FROM Drawing d " +
					"WHERE d.id = :drawingId"),
    // finds all versions of a given Drawing-Id
	@NamedQuery(name  = "Drawing.findAllVersionsOfDrawingByIdOfOriginalDrawing",
			    query =
			    	"SELECT d " +
			    	"FROM Drawing d " +
			    	"WHERE d.versionOf = :originalDrawingId"
			    	)
})

/**
 * Entity implementation class for Entity: Drawing
 *
 */
@Entity
public class Drawing implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private List<Element> elements;
	/*
	 * 'author' will be extracted from securityContext with following code:
	 * "SecurityContextHolder.getContext().getAuthentication().getName()"
	 */
	private String author;
	// value of 0 means: this Drawing entitiy is
	// *not* a version of another Drawing entity
	// (there is no Drawing entity with an Id of 0 in DB)
	private Long versionOf = 0L;
	private String versionTag;

	public Drawing() {
		super();
	}

	/*
	 * Use following constructor to generate a new version
	 * of an existing Drawing entity
	 */
	public Drawing(Drawing originalDrawing) {
		super();
		// establish semantic relationship between "version Drawing" and "original/root Drawing"
		this.setVersionOf(originalDrawing.getId());
		// create a new list for holding the Element entities of the "original/root Drawing"
		this.elements = new ArrayList<Element>();
		// copy the Element entities one by one to the new list
		for (Element el : originalDrawing.getElements()) {
			//FIXME: if the elements-mapping is lazy, all elements are null here, maybe this can be fixed by something else then eager fetching in every case.
			// get a "Deep Copy" of the Element contained in the "original/root Drawing"
			Element copiedElem = el.createDeepCopy();
			// set the Primary Key of the added Element to null
			copiedElem.setId(null);
			// change the referenced Drawing-Id of all contained Element entities
			// to this newly constructed "version Drawing"
			copiedElem.setDrawing(this);
			// add the copied Element to the list of the newly created "version Drawing"
			this.elements.add(copiedElem);
		} // for
		this.author = originalDrawing.getAuthor();
	} // constructor

	/*
	 * Use following constructor to generate a new version
	 * of an existing Drawing entity with an additional
	 * string (a free description of the version)
	 */
	public Drawing(Drawing originalDrawing, String versionTag) {
		this(originalDrawing); // use the preceding constructor
		this.setVersionTag(versionTag);
	} // constructor

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

//	/*
//	 * unidirectional relationship with foreign key on target entity
//	 * *without* join column; new in JPA 2
//	 * 'ID' is the join column in the referenced table 'ELEMENT' and
//	 * 'ID' is also the foreign key from the perspective of the 'DRAWING' table
//	 * aka: "unidirectional one-to-many target foreign key mapping" (-> JPA 2 book p. 291)
//	 */
//	@OneToMany
//	@JoinColumn(name = "DRAWING_ID")

	// Now a *bi*directional One-to-Many relation between Drawing <--> Element
	//   (Drawing is *not* the owning side of the relation)
	@OneToMany(mappedBy="drawing",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@OrderColumn(name = "ELEMENT_ORDER")
	public List<Element> getElements() {
		return elements;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setVersionOf(Long versionOf) {
		this.versionOf = versionOf;
	}

	public Long getVersionOf() {
		return versionOf;
	}

	public void setVersionTag(String versionTag) {
		this.versionTag = versionTag;
	}

	public String getVersionTag() {
		return versionTag;
	}

} //class
