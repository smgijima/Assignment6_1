package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.BusDetails;
import cput.ac.za.assignment_6_1.repository.impl.BusClassRepositoryImpl;
import cput.ac.za.assignment_6_1.repository.impl.BusDetailsRepositoryImp;

/**
 * Created by mgijma on 2016/04/28.
 */
public class BusDeailsRepositoryTest extends AndroidTestCase {

    private static final String TAG="DETAILS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        BusDetailsRepository repo = new BusDetailsRepositoryImp(this.getContext());

        // CREATE
        BusDetails createEntity = new BusDetails.Builder()
                .Busno("111")
                .Bustype("Grey hound")
                .Noofseats("30")
                .build();

        BusDetails insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<BusDetails> area = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",area.size()>0);

        //READ ENTITY
        BusDetails entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        BusDetails updateEntity = new BusDetails.Builder()
                .Busno("111")
                .Bustype("Grey hound")
                .Noofseats("30")
                .build();

        repo.update(updateEntity);
        BusDetails newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","111",newEntity.getBusNo());

        // DELETE ENTITY
        repo.delete(updateEntity);
        BusDetails deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);


    }
}
