public class Ticket {
    private String passengerName, trainNumber, classType, date, from, to;

    public Ticket(String passengerName, String trainNumber, String classType, String date, String from, String to) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    @Override
    public String toString() {
        return "Passenger: " + passengerName + ", Train: " + trainNumber + ", Class: " + classType +
               ", Date: " + date + ", From: " + from + ", To: " + to;
    }
}
