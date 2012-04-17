/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util;

import hotelsoftware.model.database.parties.DBCompany;
import hotelsoftware.model.database.room.DBRoomcategories;
import hotelsoftware.model.database.room.DBRooms;
import hotelsoftware.model.database.reservation.DBReservationoptions;
import hotelsoftware.model.database.room.DBSeasons;
import hotelsoftware.model.database.parties.DBAddress;
import hotelsoftware.model.database.service.DBService;
import hotelsoftware.model.database.room.DBRoomsroomstatus;
import hotelsoftware.model.database.room.DBRoomcategoryprices;
import hotelsoftware.model.database.reservation.DBReservations;
import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.database.room.DBRoomcategorypricesPK;
import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.reservation.DBReservationitemsPK;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.invoice.DBInvoiceitemsPK;
import hotelsoftware.model.database.room.DBRoomstatus;
import hotelsoftware.model.database.parties.DBCountry;
import hotelsoftware.model.database.service.DBServiceType;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.room.DBRoomoptions;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.users.DBRole;
import hotelsoftware.model.database.parties.DBCustomer;
import hotelsoftware.model.database.reservation.DBReservationitems;
import hotelsoftware.model.database.invoice.DBInvoiceitem;
import hotelsoftware.model.database.parties.DBPerson;
import hotelsoftware.model.database.invoice.DBPaymentmethod;
import hotelsoftware.model.database.users.DBPermission;
import hotelsoftware.model.database.room.DBRoomsroomstatusPK;
import hotelsoftware.model.database.users.DBUser;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author mohi
 */
public class HibernateUtil
{

    private static final SessionFactory sessionFactory;
    
    static
    {
        try
        {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .configure()
                    .addPackage("hotelsoftware.database.model")
                    .addAnnotatedClass(DBAddress.class)
                    .addAnnotatedClass(DBCompany.class)
                    .addAnnotatedClass(DBCompanyType.class)
                    .addAnnotatedClass(DBCountry.class)
                    .addAnnotatedClass(DBCustomer.class)
                    .addAnnotatedClass(DBExtraService.class)
                    .addAnnotatedClass(DBGuest.class)
                    .addAnnotatedClass(DBHabitation.class)
                    .addAnnotatedClass(DBInvoiceitem.class)
                    .addAnnotatedClass(DBInvoiceitemsPK.class)
                    .addAnnotatedClass(DBInvoice.class)
                    .addAnnotatedClass(DBPaymentmethod.class)
                    .addAnnotatedClass(DBPermission.class)
                    .addAnnotatedClass(DBPerson.class)
                    .addAnnotatedClass(DBReservationitems.class)
                    .addAnnotatedClass(DBReservationitemsPK.class)
                    .addAnnotatedClass(DBReservationoptions.class)
                    .addAnnotatedClass(DBReservations.class)
                    .addAnnotatedClass(DBRole.class)
                    .addAnnotatedClass(DBRoomcategories.class)
                    .addAnnotatedClass(DBRoomcategoryprices.class)
                    .addAnnotatedClass(DBRoomcategorypricesPK.class)
                    .addAnnotatedClass(DBRoomoptions.class)
                    .addAnnotatedClass(DBRooms.class)
                    .addAnnotatedClass(DBRoomsroomstatus.class)
                    .addAnnotatedClass(DBRoomsroomstatusPK.class)
                    .addAnnotatedClass(DBRoomstatus.class)
                    .addAnnotatedClass(DBSeasons.class)
                    .addAnnotatedClass(DBService.class)
                    .addAnnotatedClass(DBServiceType.class)
                    .addAnnotatedClass(DBUser.class)
                    .buildSessionFactory();
        } catch(Throwable ex)
        {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
