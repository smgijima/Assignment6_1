package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.BusClass;
import cput.ac.za.assignment_6_1.repository.impl.BusClassRepositoryImpl;

/**
 * Created by mgijma on 2016/04/27.
 */
public class BusRepositoryTest extends AndroidTestCase{

    private static final String TAG="BUS TEST";
    private Long id;


    public void testCreateReadUpdateDelete() throws Exception {
        BusClassRepository repo = new BusClassRepositoryImpl(this.getContext());

        // CREATE
        BusClass createEntity = new BusClass.Builder()
                .ticket("5")
                .type("FirstClass")
                .oneWayOrReturn("Return")
                .build();
        BusClass insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<BusClass> bus = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",bus.size()>0);

        //READ ENTITY
        BusClass entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        BusClass updateEntity = new BusClass.Builder()
                .copy(entity)
                .ticket("5")
                .build();
        repo.update(updateEntity);
        BusClass newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","5",newEntity.getTickets());

        // DELETE ENTITY
        repo.delete(updateEntity);
        BusClass deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }


}
