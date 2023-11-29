package pl.pwr.scent_tracker.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRes {
    private String login;
    private String email;
    private Long galleryId;
    private String role;
}
