package com.mangkyu.stream.Quiz2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz2 {

    private static List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X",
        "nebula", "Korea");

    // 2.1 List에 저장된 단어들의 접두사가 각각 몇개씩 있는지 Map<String, Integer>으로 변환하여라.
    // ex) ("T", 1), ("a", 2) ...

    /*
    map으로 접두사에 대해서, map으로 만든 후 리턴하기
    
     */
    public Map<String, Integer> quiz1() {

        return WORDS.stream()
                    .map(x -> x.substring(0, 1))
                    .collect(Collectors.toMap(key -> key, key -> 1, Integer::sum));
    }

    // 2.2 List에 저장된 단어들 중에서 단어의 길이가 2 이상인 경우에만, 모든 단어를 대문자로 변환하여 스페이스로 구분한 하나의 문자열로 합한 결과를 반환하여라.
    // ex) ["Hello", "a", "Island", "b"] -> “H I”

    /*
    1. filter로 길이가 2 이상인 경우만 남기기
    2. 대문자로 변환하기
    3. 변환한 문자를 1자리만 남기기
    4. joining으로 이어주기
     */
    public String quiz2() {

        return WORDS.stream()
                    .filter(x -> x.length() >= 2)
                    .map(String::toUpperCase)
                    .map(x -> x.substring(0, 1))
                    .collect(Collectors.joining(" "));
    }

}
