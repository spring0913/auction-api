package com.auction.api.controller.auction;

import com.auction.api.ApiResult;
import com.auction.api.model.commons.Id;
import com.auction.api.model.user.User;
import com.auction.api.service.auction.AuctionService;
import org.springframework.web.bind.annotation.*;

import static com.auction.api.ApiResult.OK;

@RestController
@RequestMapping("api")
public class AuctionRestController {

    private final AuctionService auctionService;

    public AuctionRestController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    // TODO Jwt Authentication
    @PostMapping(path = "auction")
    public ApiResult<AuctionDto> open(@RequestHeader("X-USER-ID") Long userId, @RequestHeader("X-USER-NAME") String name, @RequestBody AuctionRequest request){
        return OK(
            new AuctionDto(
                auctionService.open(
                    request.newAuction(Id.of(User.class, userId), name)
                )
            )
        );
    }
}
