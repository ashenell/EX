package poly.customer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository {

    private static final String FILE_PATH = "src/poly/customer/data.txt";

    private final List<AbstractCustomer> customers = new ArrayList<>();


    public Optional<AbstractCustomer> getCustomerById(String id) {
        customers.clear();
        readAll();
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void remove(String id) {
        getCustomerById(id).ifPresent(customers::remove);
        writeAll();
    }

    public void save(AbstractCustomer customer) {
        readAll();
        customers.removeIf(c -> c.getId().equals(customer.getId()));
        customers.add(customer);
        writeAll();
    }

    public int getCustomerCount() {
        readAll();
        return customers.size();
    }



    private void writeAll() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (AbstractCustomer customer : customers) {
                writer.write(customer.asString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readAll() {
        customers.clear();
        Path filePath = Paths.get(FILE_PATH);
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            reader.lines()
                    .filter(line -> !line.isBlank())
                    .map(this::parseCustomer)
                    .forEach(customers::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private AbstractCustomer parseCustomer(String line) {
        String[] p = line.split(";");
        if (p.length < 4) {
            throw new IllegalArgumentException("Last row " + line);
        }

        String type;
        String id;
        String name;
        int bonus;
        LocalDate last = null;

        if ("REGULAR".equalsIgnoreCase(p[0]) || "GOLD".equalsIgnoreCase(p[0])) {
            type  = p[0];
            id    = p[1];
            name  = p[2];
            bonus = Integer.parseInt(p[3]);
            if (p.length > 4 && !p[4].isBlank()) {
                last = LocalDate.parse(p[4]);
            }
        } else {
            id    = p[0];
            name  = p[1];
            bonus = Integer.parseInt(p[2]);
            last  = LocalDate.parse(p[3]);
            type  = (p.length > 4) ? p[4] : "REGULAR";
        }

        return "GOLD".equalsIgnoreCase(type)
                ? new GoldCustomer(id, name, bonus, last)
                : new RegularCustomer(id, name, bonus, last);
    }

    public List<AbstractCustomer> getAllPaged(int pageNumber, int pageSize) {
        return List.of();
    }
}
