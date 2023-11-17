package pl.pwr.scent_tracker.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagePerfumeSimpleRes {
    private int total;
    private List<PerfumeSimpleRes> perfumes;
}
