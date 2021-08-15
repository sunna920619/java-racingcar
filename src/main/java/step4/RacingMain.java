package step4;

import step4.domain.Car;
import step4.domain.Racing;
import step4.util.InputView;
import step4.util.ResultView;
import step4.util.Validator;

import java.util.List;
import java.util.Scanner;

public class RacingMain {
    public static void main(String[] args) {
        // 입력 받기
        InputView inputView = new InputView(new Scanner(System.in));

        String carNames = inputView.enterCarName();
        int gameCount = inputView.enterGameCount();

        Racing racing = new Racing();
        // 입력 받은 차 이름 나누기
        String[] names = racing.splitNames(carNames);

        // input check
        Validator validator = new Validator();
        validator.checkInput(names, gameCount);

        // 입력 받은 이름으로 Car 객체 생성
        List<Car> cars = racing.initCars(names);

        ResultView resultView = new ResultView();
        resultView.printGameResultHeader();
        
        // 게임 실행
        for(int i = 0; i < gameCount; i++) {
            racing.startRace(cars);
            resultView.printDistance(cars);
            resultView.printNewLine();
        }

        // 게임 결과 출력
        List<String> winners = racing.getWinner(cars);
        resultView.printGameWinners(winners);
    }
}
