package br.com.wecare.we_care_project.controller;

import br.com.wecare.we_care_project.dtos.UserDto;
import br.com.wecare.we_care_project.model.UserModel;
import br.com.wecare.we_care_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "User", description = "API para gerenciamento de usuarios")
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController extends GenericController{

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Lista todos os usuários", description = "Lista todas as estatisticas")
    @GetMapping("/")
    public ResponseEntity<Page<UserModel>> get(@PathVariable Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(pageable));
    }

    @Operation(summary = "Recupera usuario pelo ID", description = "Recupera os usuarios a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuário encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
        Optional<UserModel> optional = userService.findById(id);
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera usuario pelo nome", description = "Recupera o usuario pelo nome")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/name/{name}")
    public ResponseEntity<UserModel> getByNameContainingIgnoreCase(@PathVariable String name) {
        Optional<UserModel> optional = Optional.ofNullable(userService.findByNameContainingIgnoreCase(name));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera usuario pelo cpf", description = "Recupera usuario pelo cpf")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserModel> getByCpf(@PathVariable long cpf) {
        Optional<UserModel> optional = Optional.ofNullable(userService.findByCpf(cpf));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera usuario pelo login", description = "Recupera usuario pelo login")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/login/{login}")
    public ResponseEntity<UserModel> getByLogin(@PathVariable String  login) {
        Optional<UserModel> optional = Optional.ofNullable(userService.findByLogin(login));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera usuario pelo email", description = "Recupera usuario pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserModel> getByEmail(@PathVariable String  email) {
        Optional<UserModel> optional = Optional.ofNullable(userService.findByEmail(email));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera usuario pelo email", description = "Recupera usuario pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/email2/{email}")
    public ResponseEntity<List<UserModel>> getByEmail2(@PathVariable String  email) {
        Optional<List<UserModel>> optional = Optional.ofNullable(userService.findByEmail2(email));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Salva usuario", description = "Salva usuario")
    @ApiResponse(responseCode = "201", description = "Usuário salvo com sucesso", content = @Content(schema = @Schema(implementation = UserModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody UserDto userDto, BindingResult result) {

        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDto));

        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @Operation(summary = "Exclui um usuarioo pelo Id" , description = "Exclui um usuario a partir do seu ID")
    @ApiResponse(responseCode = "204", description = "usuario excluido com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<UserModel> optional = userService.findById(id);
        return optional
                .map(user -> {
                    try {
                        userService.deleteById(id);
                        return ResponseEntity.ok().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Altera um usuario pelo Id" , description = "Altera um usuario a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody UserDto userDto, BindingResult result) {
        Optional<UserModel> optional = userService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(userService.save(copyProperties(userDto, UserModel.class)));

        } catch (Exception e) {
            return handleErrors(e);
        }
    }

}
