import java.util.HashMap;
import java.util.Map;

public class PhoneDataBase {
    private Map<String, Phone> phones;
    private Phone currentPhone;

    public PhoneDataBase() {
        phones = new HashMap<>();
        phones.put("1", new Phone("1"));
        phones.put("2", new Phone("2"));
        phones.put("3", new Phone("3"));
        currentPhone = phones.get("1");
    }

    public void changeSIM(String simID) {
        if (phones.containsKey(simID)) {
            currentPhone = phones.get(simID);
            System.out.println("Switched to SIM: " + simID);
        } else {
            System.out.println("SIM " + simID + " not found. Available SIMs: 1,2,3");
        }
    }

    public Phone getCurrentPhone() {
        return currentPhone;
    }

    public String getCurrentSimId() {
        return currentPhone.getSimId();
    }
}