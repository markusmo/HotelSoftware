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
                    .addAnnotatedClass(Addresses.class)
                    .addAnnotatedClass(Companies.class)
                    .addAnnotatedClass(Companytypes.class)
                    .addAnnotatedClass(Countries.class)
                    .addAnnotatedClass(Customers.class)
                    .addAnnotatedClass(Extraservices.class)
                    .addAnnotatedClass(Guests.class)
                    .addAnnotatedClass(Habitations.class)
                    .addAnnotatedClass(Invoiceitems.class)
                    .addAnnotatedClass(InvoiceitemsPK.class)
                    .addAnnotatedClass(Invoices.class)
                    .addAnnotatedClass(Paymentmethods.class)
                    .addAnnotatedClass(DBPermission.class)
                    .addAnnotatedClass(Persons.class)
                    .addAnnotatedClass(Reservationitems.class)
                    .addAnnotatedClass(ReservationitemsPK.class)
                    .addAnnotatedClass(Reservationoptions.class)
                    .addAnnotatedClass(Reservations.class)
                    .addAnnotatedClass(DBRole.class)
                    .addAnnotatedClass(Roomcategories.class)
                    .addAnnotatedClass(Roomcategoryprices.class)
                    .addAnnotatedClass(RoomcategorypricesPK.class)
                    .addAnnotatedClass(Roomoptions.class)
                    .addAnnotatedClass(Rooms.class)
                    .addAnnotatedClass(Roomsroomstatus.class)
                    .addAnnotatedClass(RoomsroomstatusPK.class)
                    .addAnnotatedClass(Roomstatus.class)
                    .addAnnotatedClass(Seasons.class)
                    .addAnnotatedClass(Services.class)
                    .addAnnotatedClass(Servicetypes.class)
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
