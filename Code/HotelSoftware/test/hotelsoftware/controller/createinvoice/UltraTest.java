/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

/**
 *
 * @author Kno
 */
class UltraTest {

    public static void main(String[] args) throws asdfException, fdsaException {
        throw new asdfException(0);
    }
}

class asdfException extends Exception {

    public asdfException(int a) throws fdsaException, asdfException {
        if (a > 9) {
            throw new fdsaException(a);
        } else {
            throw new asdfException(a + 1);
        }
    }
}

class fdsaException extends Exception {

    public fdsaException(int b) throws asdfException, fdsaException {
        if (b < 0) {
        } else {
            System.out.println(b + "");
            throw new fdsaException(b - 1);
        }
    }
}
