package consoleApp;

public class OperationApp implements Cloneable {
    String operationName;
    Operation operation;

    public OperationApp(String operationName, Operation setterOperation) {
        this.operationName = operationName;
        this.operation = setterOperation;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new OperationApp(operationName, operation);
    }
}
