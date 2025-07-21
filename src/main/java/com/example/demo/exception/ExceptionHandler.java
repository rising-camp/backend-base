package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Slf4j
public class ExceptionHandler {

    public static <T> T execute(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (EntityNotFoundException e) {
//          (실제 유저) 대고객 메세지에는 내부 메세지를 그대로 전달하지 않고, 내부 메세지는 개발자들에게 로그로 확인가능하도록 한 뒤
            log.warn("데이터베이스를 확인하세요", e);
//          (실제 유저) 대고객에게는 부드러운 뉘앙스의 메세지를 전달하도록 한다 (단, 프론트엔드에겐 힌트를 줘야한다)
            throw new RuntimeException("다시 조회하거나 운영팀에 문의해주세요");
        } catch (EntitySaveFailedException e) {
            log.warn("요청을 확인하세요", e);
            throw new RuntimeException("다시 시도하거나 운영팀에 문의해주세요");
        } catch (Exception e) {
            log.error("알 수 없는 에러 발생", e);
            throw new RuntimeException("열심히 고치고 있습니다");
        }
    }
}
