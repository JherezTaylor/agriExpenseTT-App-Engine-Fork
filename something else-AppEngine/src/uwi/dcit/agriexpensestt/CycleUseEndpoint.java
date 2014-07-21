package uwi.dcit.agriexpensestt;

import uwi.dcit.agriexpensestt.EMF;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "cycleuseendpoint", namespace = @ApiNamespace(ownerDomain = "example.com", ownerName = "example.com", packagePath="agriexpensett"))
public class CycleUseEndpoint {

  /**
   * This method lists all the entities inserted in datastore.
   * It uses HTTP GET method and paging support.
   *
   * @return A CollectionResponse class containing the list of all entities
   * persisted and a cursor to the next page.
   */
  @SuppressWarnings({"unchecked", "unused"})
  @ApiMethod(name = "listCycleUse")
  public CollectionResponse<CycleUse> listCycleUse(
    @Nullable @Named("cursor") String cursorString,
    @Nullable @Named("limit") Integer limit) {

    EntityManager mgr = null;
    Cursor cursor = null;
    List<CycleUse> execute = null;

    try{
      mgr = getEntityManager();
      Query query = mgr.createQuery("select from CycleUse as CycleUse");
      if (cursorString != null && cursorString != "") {
        cursor = Cursor.fromWebSafeString(cursorString);
        query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
      }

      if (limit != null) {
        query.setFirstResult(0);
        query.setMaxResults(limit);
      }

      execute = (List<CycleUse>) query.getResultList();
      cursor = JPACursorHelper.getCursor(execute);
      if (cursor != null) cursorString = cursor.toWebSafeString();

      // Tight loop for fetching all entities from datastore and accomodate
      // for lazy fetch.
      for (CycleUse obj : execute);
    } finally {
      mgr.close();
    }

    return CollectionResponse.<CycleUse>builder()
      .setItems(execute)
      .setNextPageToken(cursorString)
      .build();
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  @ApiMethod(name = "getCycleUse")
  public CycleUse getCycleUse(@Named("id") String id) {
   // EntityManager mgr = getEntityManager();
    CycleUse cycleuse  = new CycleUse();
    DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
	  Key k=KeyFactory.stringToKey(id);
	  Entity et = null;
	  try {
		et=datastore.get(k);
	} catch (com.google.appengine.api.datastore.EntityNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(et==null)
		  return null;
	  cycleuse.setAmount((Double) et.getProperty("amount"));
	  cycleuse.setCost((Double) et.getProperty("cost"));
	  cycleuse.setCycleid((Integer) et.getProperty("cycleid"));
	  cycleuse.setPurchaseId((Integer) et.getProperty("purchaseId"));
	  cycleuse.setResource((String) et.getProperty("resource"));
    /*try {
      cycleuse = mgr.find(CycleUse.class, id);
    } finally {
      mgr.close();
    }*/
    return cycleuse;
  }

  /**
   * This inserts a new entity into App Engine datastore. If the entity already
   * exists in the datastore, an exception is thrown.
   * It uses HTTP POST method.
   *
   * @param cycleuse the entity to be inserted.
   * @return The inserted entity.
   */
  @ApiMethod(name = "insertCycleUse")
  public CycleUse insertCycleUse(CycleUse cycleuse) {
	Key k=KeyFactory.createKey("CycleUse", cycleuse.getId());
	cycleuse.setKey(k);
	cycleuse.setKeyrep(KeyFactory.keyToString(k));
    EntityManager mgr = getEntityManager();
    try {
      if(containsCycleUse(cycleuse)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.persist(cycleuse);
    } finally {
      mgr.close();
    }
    cycleuse.setAccount(KeyFactory.keyToString(k));//using account to store the string rep of the key
    return cycleuse;
  }

  /**
   * This method is used for updating an existing entity. If the entity does not
   * exist in the datastore, an exception is thrown.
   * It uses HTTP PUT method.
   *
   * @param cycleuse the entity to be updated.
   * @return The updated entity.
   */
  @ApiMethod(name = "updateCycleUse")
  public CycleUse updateCycleUse(CycleUse cycleuse) {
    EntityManager mgr = getEntityManager();
    try {
      if(!containsCycleUse(cycleuse)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.persist(cycleuse);
    } finally {
      mgr.close();
    }
    return cycleuse;
  }

  /**
   * This method removes the entity with primary key id.
   * It uses HTTP DELETE method.
   *
   * @param id the primary key of the entity to be deleted.
   */
  @ApiMethod(name = "removeCycleUse")
  public void removeCycleUse(@Named("id") String id) {
	  Key k=KeyFactory.stringToKey(id);
	  DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
		try{
			datastore.delete(k);
		}catch(Exception e){
			return;
		}
   /* EntityManager mgr = getEntityManager();
    try {
      CycleUse cycleuse = mgr.find(CycleUse.class, id);
      mgr.remove(cycleuse);
    } finally {
      mgr.close();
    }*/
  }

  private boolean containsCycleUse(CycleUse cycleuse) {
    EntityManager mgr = getEntityManager();
    boolean contains = true;
    try {
      CycleUse item = mgr.find(CycleUse.class, cycleuse.getKey());
      if(item == null) {
        contains = false;
      }
    } finally {
      mgr.close();
    }
    return contains;
  }

  private static EntityManager getEntityManager() {
    return EMF.get().createEntityManager();
  }

}