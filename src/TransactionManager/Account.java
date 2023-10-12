package TransactionManager;

public abstract class Account implements Comparable<Account> {
    /* an abstract class is essentially a blueprint for other classes to inherit
        so an abstract class cannot be instantiated itself, it will be used as a template
        for other class objects to be instantiated

        abstract methods are essentially methods that are not implemented
        they will be inherited and implemented by the subclasses that use this template

        there can be concrete methods that subclasses can inherit or choose to overrride

    */
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();
}