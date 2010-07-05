package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*
 * Named Queries for Entity Element
 */

@NamedQueries({
	@NamedQuery(name  = "Element.findElementById",
	            query = 
	            	"SELECT e " +
	            	"FROM Element e " +
	            	"WHERE e.id = :elementId"),		
	            	
	@NamedQuery(name  = "Element.findElementByDrawingIdAndElementId",
		        query =
		    	"SELECT elem " +
	            "FROM Drawing d JOIN d.elements elem " +
			    "WHERE d.id = :drawingId AND " +
			    "elem.id = :elementId" )	
})

/**
 * Entity implementation class for Entity: Element
 *
 */
@Entity
public class Element implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String type;
	private String styleXML;
	private int elemOrder;
	private double[] coords;
	private Long elemVersion = 0L;
	
	/*
	 * Speichert die Id des Drawings, in der dieses Element enthalten ist.
	 * Diese Id wird benoetigt, um in einem separaten Schritt die Drawing-Entity
	 * mit der hier vorgehaltenen Id herauszusuchen und dem Element zuzuordnen.
	 */
	private long drawingId;
	
	private Drawing drawing;
	
	public Element() {
		super();
	}
	
	public Element(long drawingId) {
		super();
		this.setDrawingId(drawingId);
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setStyleXML(String styleXML) {
		this.styleXML = styleXML;
	}

	public String getStyleXML() {
		return styleXML;
	}

	public void setElemOrder(int elemOrder) {
		this.elemOrder = elemOrder;
	}

	public int getElemOrder() {
		return elemOrder;
	}

	public void setCoords(double[] coords) {
		this.coords = coords;
	}
	
	// DB data type 'longblob' required for Path-Element
	@Column(columnDefinition="longblob")
	public double[] getCoords() {
		return coords;
	}

	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}
	
	// *Bi*directional Many-to-One relation between Element <--> Drawing 
	@ManyToOne
	@JoinColumn(name="DRAWING_ID")
	public Drawing getDrawing() {
		return drawing;
	}

	public void setDrawingId(long drawingId) {
		this.drawingId = drawingId;
	}

	public long getDrawingId() {
		return drawingId;
	}

	public void setElemVersion(Long elementVersion) {
		this.elemVersion = elementVersion;
	}

	public Long getElemVersion() {
		return elemVersion;
	}	
	
	public Element createDeepCopy() {
		// helper method for creating a so called "Deep Copy"
		// of *this* Element object
		// see: http://www.informatik-student.de/2006/12/10/deep-copy-von-java-objekten/
		Element deepCopy = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
			ObjectOutputStream    oos  = new ObjectOutputStream(baos);
			// write the object itself to the Object Output Stream
			oos.writeObject(this);
			ByteArrayInputStream bais  = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois      = new ObjectInputStream(bais);
			// read the object in again
			deepCopy = (Element) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // try-catch
		// return the new and self-contained Element object to the caller
		return deepCopy;
	} // createDeepCopy

} // class

