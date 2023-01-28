package net.mwav.sala.authentication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RefreshRequest implements Serializable {

	private static final long serialVersionUID = 4071025165318282640L;

	@NotBlank
	private String accessToken;

}
