package step3.domain;

import step3.util.Validator;

import java.util.*;

public class Racing {

    private static final String SEPARATOR = ",";

    private Random random;
    private int gameCount = 0;
    private List<Car> cars;

    public Racing(Random random) {
        this.random = random;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public void initRace(String carNames, int gameCount) {
        // 입력 받은 차 이름 나누기
        String[] names = carNames.split(SEPARATOR);

        // input check
        Validator validator = new Validator();
        validator.checkInput(names, gameCount);

        // 입력 받은 이름으로 Car 객체 생성
        cars = initCars(names);
    }

    public List<Car> initCars(String[] names) {
        List<Car> cars = new ArrayList<>();

        for(int i = 0; i < names.length; i++) {
            cars.add(new Car(names[i].trim()));
        }

        return cars;
    }

    public void startRace() {
        for(Car car : cars) {
            car.move(random.nextInt(10));
        }
    }

    public List<String> getWinner() {
        // distance 기준 역순으로 정렬
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car s1, Car s2) {
                return s2.getDistance() - s1.getDistance();
            }
        });

        List<String> winners = new ArrayList<>();
        int winnerDistance = 0;
        for(Car car : cars) {
            winnerDistance = addWinner(car, winnerDistance, winners);
        }

        return winners;
    }

    public int addWinner(Car car, int winnerDistance, List<String> winners) {
        if (winnerDistance <= car.getDistance()) {
            winners.add(car.getName());
            winnerDistance = car.getDistance();
        }

        return winnerDistance;
    }
}
