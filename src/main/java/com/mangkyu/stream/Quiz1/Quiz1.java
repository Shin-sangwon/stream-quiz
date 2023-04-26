package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();

        /*
        1. 리스트의 요소는 배열이므로 배열 중 필요한 정보인 취미만 가져오기
        2. 사람마다 취미가 여러개일수도 있으므로, 취미를 배열로 만든 후 flayMap 으로 평탄화 하기
        3. 가져온 요소를 map에 넣고, 이미 있을 경우 +1 해주기
        =================틀림====================
        4. 왜? 배열로 받아와서 쪼갤 때 앞의 whitespace때문에 " 개발"과 "개발"이 다르게 인식됨.
        5. trim()으로 해결!!

         */
        return csvLines.stream()
                       .map(array -> array[1].trim())
                       .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                       .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, Integer::sum));
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.

    /*
    1-1과 똑같이 접근하기
    1. 정씨 성을 가진 사람만 남기기
    2. 남은 사람 중에서, 1-1과 같은 작업 하기
    3. 그런데, 다른 방법으로도 해보자
    4. Collectors.counting()을 이용해 요소의 개수 얻기
    ==============틀림===============
    4-1. 이론상으로 될 것 같았으나, counting()은 Long을 return하여 2L과 같은 값이 입력되어 틀림.
    5. collectingAndThen 메소드를 이용함
    --> collectingAndThen은 Collecting을 진행한 후 그 결과로 메소드를 하나 더 호출할 수 있게 해줌
    따라서, Collectors.counting()로 값을 Long으로 카운팅 한 뒤에, intvalue로 변환하여 해결함.

     */
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        final String lastName = "정";

        return csvLines.stream()
                       .filter(name -> name[0].trim()
                                              .startsWith(lastName))
                       .map(array -> array[1].trim())
                       .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
                       .collect(Collectors.groupingBy(hobby -> hobby,
                           Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        //.collect(Collectors.toMap(hobby -> hobby, hobby -> 1, Integer::sum));
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.

    /*
    틀림... 다시풀어야됨
     */
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        final String index = ".*좋아*.";

        return csvLines.stream()
                       .map(lines -> lines[2].trim())
                       .flatMap(line -> Arrays.stream(line.split("\\W+")))
                       .reduce(0, (count, word) -> word.matches(index) ? count + 1 : count, Integer::sum);
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv")
                                                                     .getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
