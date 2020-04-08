package pt.ulisboa.tecnico.sise.lab03.dummynotepad.DataModel;

public class Person {

    protected String _name;
    protected int _fiscalNumber;
    protected String _address;
    protected String _dateOfBirth;

    public Person(String name, int fiscalNumber, String address, String dateOfBirth) {

        _name = name;
        _fiscalNumber = fiscalNumber;
        _address = address;
        _dateOfBirth = dateOfBirth;
    }

    public Person(Person p) {
        _name = p.getName();
        _fiscalNumber = p.getFiscalNumber();
        _address = p.getAddress();
        _dateOfBirth = p.getDateOfBirth();
    }


    public String getName() {
            return _name;
        }

        public void setName(String name) {
            _name = name;
        }

        public int getFiscalNumber() {
            return _fiscalNumber;
        }

        public void setFiscalNumber(int fiscalNumber) {
            _fiscalNumber = fiscalNumber;
        }

        public String getAddress() {
            return _address;
        }

        public void setAddress(String address) {
            _address = address;
        }

        public String getDateOfBirth() {
            return _dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            _dateOfBirth = dateOfBirth;
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
        if (_fiscalNumber != other._fiscalNumber) {
            return false;
        }
        return true;
        }

        public String toString() {
        return "Customer Name:" + _name + ", " +
                "Fiscal Number:" + _fiscalNumber + ", " +
                "Address:" + _address + ", " +
                "Date of Birth:" + _dateOfBirth + ".";
    }
}

