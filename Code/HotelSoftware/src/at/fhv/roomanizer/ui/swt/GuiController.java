package at.fhv.roomanizer.ui.swt;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.application.DayEndClosingController;
import at.fhv.roomanizer.application.HabitationController;
import at.fhv.roomanizer.application.PrePaymentController;
import at.fhv.roomanizer.domain.IHabitation;
import at.fhv.roomanizer.ui.swt.habitation.HabitationTable;
import at.fhv.roomanizer.ui.swt.habitation.HabitationView;

/**
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class GuiController {
	/**
	 * The only instance of the gui controller
	 */
	private static GuiController _instance = null;
	
	/**
	 * private constructor
	 */
	private GuiController(){
		
	}
	
	/**
	 * Returns the only instance of the gui controller, and creates it before, if needed
	 * @return The only instance
	 */
	public static GuiController getInstance(){
		if(_instance == null){
			_instance = new GuiController();
		}
		return _instance;
	}

	public void initHabitationTable(HabitationTable habitationTable){
		try{
			HabitationController habitationController = new HabitationController();
			List<IHabitation> habitations = habitationController.loadAllHabitations();
			habitationTable.paint(habitations);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void dayEndClosing(){
		try{
			DayEndClosingController dayEndClosingController = new DayEndClosingController();
			dayEndClosingController.runDayEndClosing(new Date());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void prepayment(HabitationView habitationView) throws NumberFormatException {
		try {
			int selectedIndex = habitationView.getHabitationTable().getTable().getSelectionIndex();
			int habitationId = ((IHabitation) habitationView.getHabitationTable().getTable().getItem(selectedIndex).getData()).getId();
			float amount = Float.parseFloat(habitationView.getPrepayment());
			PrePaymentController prepaymentController = new PrePaymentController();
			prepaymentController.savePrePayment(habitationId, amount, new Date());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			if(e instanceof NumberFormatException){
				throw e;
			}
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
