package uk.co.roteala.api.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.roteala.api.ResultStatus;
import uk.co.roteala.common.Block;
import uk.co.roteala.common.monetary.Coin;
import uk.co.roteala.common.monetary.CoinConverter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockResponse {
    private String blockHash;
    private Block block;
    @JsonSerialize(converter = CoinConverter.class)
    private Coin totalFees;
    @JsonSerialize(converter = CoinConverter.class)
    private Coin totalValue;
    private int totalTransactions;
    private String message;
    private ResultStatus result;
}
