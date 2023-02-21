package bookrecommendation.book.controller.rest;

import bookrecommendation.book.controller.status.LoginResponse;
import bookrecommendation.book.controller.status.ResponseMessage;
import bookrecommendation.book.controller.status.StatusCode;
import bookrecommendation.book.domain.Member;
import bookrecommendation.book.dto.BarcodeDto;
import bookrecommendation.book.dto.MemberFoundResponseDto;
import bookrecommendation.book.dto.RfidDto;
import bookrecommendation.book.service.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Transactional
public class MemberRestController {
    private final MemberService memberService;
    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Rfid 인식 시 회원 검증
     * @param rfidDto
     * @return ResponseEntity.status.body (Body Params in REST) - 회원 기본 정보 반환
     */
    @PostMapping(value = "/loginByRfid")
    public ResponseEntity<LoginResponse> loginByRfid(@RequestBody RfidDto rfidDto){
        String rfid = rfidDto.getRfid();
        Optional<Member> memberFind = memberService.validateByRfid(rfid);

        LoginResponse response = new LoginResponse();

        if(memberFind.isPresent()){
            response.setCode(StatusCode.OK);
            response.setMessage(ResponseMessage.LOGIN_SUCCESS);
            response.setData(setResponseDto(memberFind.get()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Barcode 인식 시 회원 검증
     * @param barcodeDto
     * @return ResponseEntity.status.body (Body Params in REST) - 회원 기본 정보 반환 
     */

    @PostMapping(value = "/loginByBarcode")
    public ResponseEntity<LoginResponse> loginByBarcode(@RequestBody BarcodeDto barcodeDto){
        String barcode = barcodeDto.getBarcode();
        Optional<Member> memberFind = memberService.validateByBarcode(barcode);

        LoginResponse response = new LoginResponse();

        if(memberFind.isPresent()){
            response.setCode(StatusCode.OK);
            response.setMessage(ResponseMessage.LOGIN_SUCCESS);
            response.setData(setResponseDto(memberFind.get()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 회원 인증 성공 및 실패에 따른 ResponseDto 변환
     * @param member
     * @return ResponseDto
     */
    private MemberFoundResponseDto setResponseDto(Member member){
        MemberFoundResponseDto responseDto = new MemberFoundResponseDto();

        responseDto.setId(String.valueOf(member.getId()));
        responseDto.setGender(member.getGender());
        responseDto.setName(member.getName());
        responseDto.setAge(member.getAge());

        return responseDto;
    }
}
