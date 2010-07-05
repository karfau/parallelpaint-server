package services;

import java.util.List;

import model.Drawing;
import model.Element;
import exceptions.DrawingNotFoundException;
import exceptions.ElementNotFoundException;
import exceptions.IsAllreadyAVersionOfADrawingException;
import exceptions.OperationNotAllowedException;

public interface IElementPersistanceService {

	public Drawing createDrawing();

	public long setRegisteredDrawingAuthor(long drawingId,String authorKey) throws
		DrawingNotFoundException, OperationNotAllowedException;

	public long addElement(Element element);

	public Drawing findDrawingById(long drawingId) throws DrawingNotFoundException;

	public void updateElement(Element element);

	public void removeElement(Long elementId) throws ElementNotFoundException;

	public String selectElement(Long elementId) throws ElementNotFoundException;

	public String selectSoleElement(Long elementId) throws ElementNotFoundException;

	public String unselectElement(Long elementId) throws ElementNotFoundException;

	public long createVersionOfDrawing(Long originalDrawingId, String versionTag) throws
		DrawingNotFoundException, IsAllreadyAVersionOfADrawingException;

	public List<Drawing> getAllVersionsOfDrawing(Long originalDrawingId);

} // interface
