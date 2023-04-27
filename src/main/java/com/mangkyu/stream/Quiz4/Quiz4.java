package com.mangkyu.stream.Quiz4;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Quiz4 {

    private List<Transaction> transactions;

    public Quiz4() {
        Trader kyu = new Trader("Kyu", "Seoul");
        Trader ming = new Trader("Ming", "Gyeonggi");
        Trader hyuk = new Trader("Hyuk", "Seoul");
        Trader hwan = new Trader("Hwan", "Busan");

        transactions = Arrays.asList(
            new Transaction(kyu, 2019, 30000),
            new Transaction(kyu, 2020, 12000),
            new Transaction(ming, 2020, 40000),
            new Transaction(ming, 2020, 7100),
            new Transaction(hyuk, 2019, 5900),
            new Transaction(hwan, 2020, 4900)
        );
    }

    // 4.1 2020년에 일어난 모든 거래 내역을 찾아 거래값을 기준으로 오름차순 정렬하라.

    /*
    1. filter로 2020년 거래 내역만 남기기
    2. 거래값 기준 정렬하기
    3. 리스트로 반환하기

     */
    public List<Transaction> quiz1() {

        return transactions.stream()
                           .filter(transaction -> transaction.getYear() == 2020)
                           .sorted((o1, o2) -> o1.getValue() - o2.getValue())
                           .collect(Collectors.toList());
    }

    // 4.2 거래 내역이 있는 거래자가 근무하는 모든 도시를 중복 없이 나열하라.
    /*
    1. map으로 city를 가져오기 (가독성 위해 2번 나눠서했는데 성능적으로 안좋을 듯)
    2. distinct로 중복 제거하기
    3. 리스트로 반환하기
     */
    public List<String> quiz2() {

        return transactions.stream()
                           .map(Transaction::getTrader)
                           .map(Trader::getCity)
                           .distinct()
                           .collect(Collectors.toList());
    }

    // 4.3 서울에서 근무하는 모든 거래자를 찾아서 이름순서대로 정렬하라.
    /*
    1 map으로 트레이더 뽑아오기
    2. seoul에서 근무하는 트레이더로 필터링
    3. 거래기록이 한개가 아닐수도 있으므로 중복 제거
    4. 이름기준 정렬하기 (o1, o2) -> o1.getName().compareTo(o2.getName()) 으로 했었는데, 메소드 참조로 변경
    5. 리스트로 반환하기
     */
    public List<Trader> quiz3() {

        final String SEOUL = "SEOUL";

        return transactions.stream()
                           .map(Transaction::getTrader)
                           .filter(trader -> trader.getCity()
                                                   .equalsIgnoreCase(SEOUL))
                           .distinct()
                           .sorted(Comparator.comparing(Trader::getName))
                           .collect(Collectors.toList());


    }

    // 4.4 모든 거래자의 이름을 구분자(",")로 구분하여 정렬하라.
    
    /*
    1. map으로 트레이더 뽑아오기
    2. 뽑아온 트레이더들을 이름 순으로 정렬해놓기
    3. 정렬된 순서에서 이름만 뽑아내기
    4. 중복 제거하기
    5. , 으로 이어주기
    
     */
    public String quiz4() {

        return transactions.stream()
                           .map(Transaction::getTrader)
                           .sorted(Comparator.comparing(Trader::getName))
                           .map(Trader::getName)
                           .distinct()
                           .collect(Collectors.joining(","));
    }

    // 4.5 부산에 거래자가 있는지를 확인하라.
    
    /*
    1. Transaction에서 map으로 trader, city 가져오기
    2. 부산이랑 일치하는게 있는지 찾기
    
     */
    public boolean quiz5() {

        final String BUSAN = "BUSAN";

        return transactions.stream()
                           .map(Transaction::getTrader)
                           .map(Trader::getCity)
                           .anyMatch(city -> city.equalsIgnoreCase(BUSAN));
    }

    // 4.6 서울에 거주하는 거래자의 모든 거래 금액을 구하라.
    public List<Integer> quiz6() {
        return Collections.emptyList();
    }

    // 4.7 모든 거래 내역중에서 거래 금액의 최댓값과 최솟값을 구하라. 단, 최댓값은 reduce를 이용하고 최솟값은 stream의 min()을 이용하라.
    public Integer[] quiz7() {
        return new Integer[]{0, 0};
    }

}
