/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database;

import hotelsoftware.database.model.*;
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
                    .addAnnotatedClass(DBAddresses.class)
                    .addAnnotatedClass(DBCompanies.class)
                    .addAnnotatedClass(DBCompanytypes.class)
                    .addAnnotatedClass(DBCountries.class)
                    .addAnnotatedClass(DBCustomers.class)
                    .addAnnotatedClass(DBExtraservices.class)
                    .addAnnotatedClass(DBGuests.class)
                    .addAnnotatedClass(DBHabitations.class)
                    .addAnnotatedClass(DBInvoiceitems.class)
                    .addAnnotatedClass(DBInvoiceitemsPK.class)
                    .addAnnotatedClass(DBInvoices.class)
                    .addAnnotatedClass(DBPaymentmethods.class)
                    .addAnnotatedClass(DBPermission.class)
                    .addAnnotatedClass(DBPersons.class)
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
                    .addAnnotatedClass(DBServices.class)
                    .addAnnotatedClass(DBServicetypes.class)
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
