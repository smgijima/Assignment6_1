package cput.ac.za.assignment_6_1.repository;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import cput.ac.za.assignment_6_1.domain.Ticket;
import cput.ac.za.assignment_6_1.repository.impl.TicketRepositoryImp;

/**
 * Created by mgijma on 2016/04/28.
 */
public class TicketRepositoryTest extends AndroidTestCase {

    private static final String TAG="BUS STATION TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        TicketRepository repo = new TicketRepositoryImp(this.getContext());

        // CREATE
        Ticket createEntity = new Ticket.Builder()
                .ticketid("1234")
                .Date("12/05/16")
                .BusNo("M34")
                .SeatNo("4")
                .Status("Available")
                .CheckingStatus("Pending..")
                .build();

        Ticket insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Ticket> area = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", area.size() > 0);

        //READ ENTITY
        Ticket entity = repo.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);

        //UPDATE ENTITY
        Ticket updateEntity = new Ticket.Builder()
                .ticketid("1234")
                .Date("12/05/16")
                .BusNo("M34")
                .SeatNo("4")
                .Status("Available")
                .CheckingStatus("Pending..")
                .build();


        repo.update(updateEntity);
        Ticket newEntity = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "M34", newEntity.getBusNo());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Ticket deletedEntity = repo.findById(id);
        Assert.assertNotNull(TAG + " DELETE", deletedEntity);
    }

}
