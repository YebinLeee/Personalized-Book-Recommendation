package bookrecommendation.book.controller;

import bookrecommendation.book.dto.BarcodeDto;
import bookrecommendation.book.dto.MemberFoundResponseDto;
import bookrecommendation.book.dto.RfidDto;
import bookrecommendation.book.domain.Member;
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
    public ResponseEntity<MemberFoundResponseDto> loginByRfid(@RequestBody RfidDto rfidDto){
        String rfid = rfidDto.getRfid();
        Optional<Member> memberFind = memberService.validateByRfid(rfid);
        return ResponseEntity.status(HttpStatus.OK).body(setResponseDto(memberFind));
    }

    /**
     * Barcode 인식 시 회원 검증
     * @param barcodeDto
     * @return ResponseEntity.status.body (Body Params in REST) - 회원 기본 정보 반환 
     */

    @PostMapping(value = "/loginByBarcode")
    public ResponseEntity<MemberFoundResponseDto> loginByBarcode(@RequestBody BarcodeDto barcodeDto){
        String barcode = barcodeDto.getBarcode();
        Optional<Member> memberFind = memberService.validateByBarcode(barcode);
        return ResponseEntity.status(HttpStatus.OK).body(setResponseDto(memberFind));
    }

    /**
     * 회원 인증 성공 및 실패에 따른 ResponseDto 변환
     * @param memberFind (Present or else Empty)
     * @return ResponseDto
     */
    private MemberFoundResponseDto setResponseDto(Optional<Member> memberFind){
        MemberFoundResponseDto responseDto = new MemberFoundResponseDto();

        if(memberFind.isEmpty()){
            responseDto.setIs_authenticated('N');
        }
        else{
            Member member = memberFind.get();

            responseDto.setIs_authenticated('Y');
            responseDto.setId(member.getId());
            responseDto.setGender(member.getGender());
            responseDto.setName(member.getName());
            responseDto.setAge(member.getAge());
        }
        return responseDto;
    }
}
