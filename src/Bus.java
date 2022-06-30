public class Bus {

    Integer BusNum;
    int Max_passenger = 0;
    int Real_passenger = 0;
    int Fare = 0;
    int Oil_Volume = 100;
    int Real_speed = 0;
    String Situation = "Race";

    public Bus(Integer busNum, int max_passenger, int fare, int real_speed) {
        BusNum = busNum;
        Max_passenger = max_passenger;
        Fare = fare;
        Real_speed = real_speed;
    }

    boolean BusOil_check() {
        boolean flag = true;
        if (this.Oil_Volume < 10) { // 주유량 떨어질때
            this.Situation = "Stop";
            this.Real_passenger = 0;
            System.out.println("**********************************************");
            System.out.println("운행을 정지합니다 차고지로 이동합니다. 남아있는 승객을 하차 시킵니다.");
            flag = false;
            System.out.println("주유량을 확인해 주세요.");
            System.out.println("**********************************************");
        }
        return flag;
    }

    public void BusStateChange(int select_num, String val) {
        int temp = 0;
        switch (select_num) {
            case 1 -> {// 차량상태 변경
                try {
                    if (!Situation.equals(val))
                        Situation = val;
                    if (Situation.equals("Race"))
                        BusOil_check();
                } catch (NumberFormatException e) {
                    System.out.println("값을 잘못 입력 하셨습니다.");

                }
            }
            case 2 -> { //오일량 변경
                temp = Integer.parseInt(val);
                if (Oil_Volume != temp) {
                    Oil_Volume = temp;
                }
                BusOil_check();
            }
            default -> {
            }
        }
    }

    public void BusPassenger_Change(int number, int person_num) {
        if (Situation.equals("Race")) {
            switch (number) {
                case 1 -> {  // 탑승
                    //최대 승객수 구함
                    if (Max_passenger < Real_passenger + person_num) {
                        System.out.println("\n***** 최대 승객을 넘었습니다. 수용 인원만 탑승합니다. *****\n");
                        Real_passenger = Real_passenger + (Max_passenger - Real_passenger);
                    } else
                        Real_passenger += person_num;
                }
                case 2 -> {  // 감소
                    if (Real_passenger - person_num < 0)
                        System.out.println("\n***** 탑승 인원보다 많은 인원을 설정했습니다. 변경하지 않습니다. *****\n");
                    else
                        Real_passenger -= person_num;
                }
                default -> {
                }
            }
        } else
            System.out.println("\n***** 운행중이 아닙니다. *****\n");
    }


    public void Speed_Change(int number, int speed_val) {
        if (BusOil_check()) {
            switch (number) {
                case 1 -> {  // 속도증가
                    Real_speed += speed_val;
                }
                case 2 -> {  // 속도감소
                    if (Real_speed - speed_val < 0)
                        System.out.println("\n***** 0보다 작습니다. 변경하지 않습니다. *****\n");
                    else
                        Real_passenger -= speed_val;
                }
                default -> {
                }
            }
        }
    }

    public void Businfo() {
        System.out.println("**********************************************");

        System.out.println("버스번호: " + BusNum + ", 요금: " + Fare + "원");
        System.out.println("최대승객수: " + Max_passenger + ", 현재승객수: " + Real_passenger);
        System.out.println("현재속도: " + Real_speed);
        System.out.println("주유량: " + Oil_Volume + ", 상태: " + Situation);
        System.out.println("**********************************************");
    }
}