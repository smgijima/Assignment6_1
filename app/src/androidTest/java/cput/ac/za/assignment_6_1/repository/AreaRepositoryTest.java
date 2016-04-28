package cput.ac.za.assignment_6_1.repository;
import android.test.AndroidTestCase;
import junit.framework.Assert;
import java.util.Set;
import cput.ac.za.assignment_6_1.domain.Area;
import cput.ac.za.assignment_6_1.repository.impl.AreaRepositoryImpl;

/**
 * Created by mgijma on 2016/04/24.
 */
public class AreaRepositoryTest extends AndroidTestCase {
    private static final String TAG="AREA TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        AreaRepository repo = new AreaRepositoryImpl(this.getContext());

        // CREATE
        Area createEntity = new Area.Builder()
                .code("67")
                .adress("Montclair")
                .build();

        Area insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Area> area = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",area.size()>0);

        //READ ENTITY
        Area entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Area updateEntity = new Area.Builder()
                .copy(entity)
                .code("67")
                .build();
        repo.update(updateEntity);
        Area newEntity = repo.findById(id);
      Assert.assertEquals(TAG+ " UPDATE ENTITY","67",newEntity.getCode());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Area deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);


    }
}
