package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.BusRegistration;
import cput.ac.za.assignment_6_1.repository.impl.BusRegRepositoryImpl;

/**
 * Created by mgijma on 2016/04/28.
 */
public class BusRegRepositoryTest extends AndroidTestCase {


    private static final String TAG="REGISTRATION_TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        BusRegRepository repo = new BusRegRepositoryImpl(this.getContext());

        // CREATE
        BusRegistration createEntity = new BusRegistration.Builder()
                .busRegNumber("001")
                .date("Siphiwo")
                .build();

        BusRegistration insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<BusRegistration> area = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",area.size()>0);

        //READ ENTITY
        BusRegistration entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        BusRegistration updateEntity = new BusRegistration.Builder()
                .busRegNumber("001")
                .date("Siphiwo")
                .build();
        repo.update(updateEntity);
        BusRegistration newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Siphiwo",newEntity.getDate());

        // DELETE ENTITY
        repo.delete(updateEntity);
        BusRegistration deletedEntity = repo.findById(id);
        Assert.assertNotNull(TAG+" DELETE",deletedEntity);



    }

}
