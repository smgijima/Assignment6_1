package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.BusDriver;
import cput.ac.za.assignment_6_1.repository.impl.BusDriverRepositoryImp;

/**
 * Created by mgijma on 2016/04/28.
 */
public class BusDriverRepositoryTest extends AndroidTestCase {

    private static final String TAG="AREA TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        BusDriverRepository repo = new BusDriverRepositoryImp(this.getContext());

        // CREATE
        BusDriver createEntity = new BusDriver.Builder()
                .empNum("001")
                .name("Siphiwo")
                .surname("Mgijima")
                .build();

        BusDriver insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<BusDriver> area = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",area.size()>0);

        //READ ENTITY
        BusDriver entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        BusDriver updateEntity = new BusDriver.Builder()
                .empNum("001")
                .name("Siphiwo")
                .surname("Mgijima")
                .build();
        repo.update(updateEntity);
        BusDriver newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","67",newEntity.getEmp_Num());

        // DELETE ENTITY
        repo.delete(updateEntity);
        BusDriver deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);


    }

}
