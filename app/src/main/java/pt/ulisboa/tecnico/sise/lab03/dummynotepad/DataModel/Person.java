package pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel;

public class Person {

    protected String name;
    protected int fiscalNumber;
    protected String address;
    protected String dateOfBirth;

    public Person(String name, int fiscalNumber, String address, String dateOfBirth) {

        name = name;
        fiscalNumber = fiscalNumber;
        address = address;
        dateOfBirth = dateOfBirth;
    }

    public Person(Person p) {
        name = p.getName();
        fiscalNumber = p.getFiscalNumber();
        address = p.getAddress();
        dateOfBirth = p.getDateOfBirth();
    }


    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFiscalNumber() {
            return fiscalNumber;
        }

        public void setFiscalNumber(int fiscalNumber) {
            this.fiscalNumber = fiscalNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person) obj;
        if (fiscalNumber != other.fiscalNumber) {
            return false;
        }
        return true;
        }

        public String toString() {
        return "Customer Name:" + name + ", " +
                "Fiscal Number:" + fiscalNumber + ", " +
                "Address:" + address + ", " +
                "Date of Birth:" + dateOfBirth + ".";
    }
}

