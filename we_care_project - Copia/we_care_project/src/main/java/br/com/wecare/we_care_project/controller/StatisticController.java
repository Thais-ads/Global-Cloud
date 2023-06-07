package br.com.wecare.we_care_project.controller;

import br.com.wecare.we_care_project.dtos.StatisticsDto;
import br.com.wecare.we_care_project.model.StatisticsModel;
import br.com.wecare.we_care_project.service.StatisticsService;
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
import java.util.Optional;

@Tag(name = "Statistics", description = "API para gerenciamento de estatisticas")
@RequestMapping("/statistics")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class StatisticController extends GenericController{
    final
    StatisticsService statisticsService;

    public StatisticController(StatisticsService statisticsService) {this.statisticsService = statisticsService;}

    @Operation(summary = "Lista todos as estatisticas", description = "Lista todas as estatisticas")
    @GetMapping("/")
    public ResponseEntity<Page<StatisticsModel>> get(@PathVariable Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getAll(pageable));
    }

    @Operation(summary = "Recupera statisticas pelo ID", description = "Recupera os dados a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<StatisticsModel> getById(@PathVariable Long id) {
        Optional<StatisticsModel> optional = statisticsService.findById(id);
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas pelo nome da cidade", description = "Recupera os dados a partir do nome da cidade")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/city/{city}")
    public ResponseEntity<StatisticsModel> findByCity(@PathVariable String city) {
        Optional<StatisticsModel> optional = Optional.ofNullable(statisticsService.findByCityContainingIgnoreCase(city));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas pela força de trabalho empregada", description = "Recupera os dados a partir do nome da força de trabalho empregada")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/workingforce/{workingforce}")
    public ResponseEntity<Page<StatisticsModel>> getByWorkingforce(@PathVariable float workingforce, @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size){
        Optional<Page<StatisticsModel>> optional = Optional.ofNullable(statisticsService.findByWorkingForce(workingforce, page, size));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas pela nivel de educação", description = "Recupera os dados a partir do nivel de educação")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/educationlevel/{educationlevel}")
    public ResponseEntity<Page<StatisticsModel>> getByEducationLevel(@PathVariable float educationLevel, @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        Optional<Page<StatisticsModel>> optional = Optional.ofNullable(statisticsService.findByEducationLevel(educationLevel, page, size));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas pelo nivel de pobreza", description = "Recupera os dados a partir do nivel de pobreza")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/poverty/{poverty}")
    public ResponseEntity<Page<StatisticsModel>> getByPoverty(@PathVariable float poverty, @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size){
        Optional<Page<StatisticsModel>> optional = Optional.ofNullable(statisticsService.findByPoverty(poverty, page, size));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas pela faixa salarial", description = "Recupera os dados a partir da faixa salarial")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/salary/{salary}")
    public ResponseEntity<Page<StatisticsModel>> getBySalary(@PathVariable float salary, @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        Optional<Page<StatisticsModel>> optional = Optional.ofNullable(statisticsService.findBySalary(salary, page, size));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas pela quantidade de pessoas pobres", description = "Recupera os dados a partir da quantidade de pessoas pobres")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/povertypercentage/{povertypercentage}")
    public ResponseEntity<Page<StatisticsModel>> getByPovertyPercentage(@PathVariable float povertyPercentage, @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size){
        Optional<Page<StatisticsModel>> optional = Optional.ofNullable(statisticsService.findByPovertyPercentage(povertyPercentage, page, size));
        return optional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Recupera statisticas trazendo todos que tenham um valor maior ou igual que o passado", description = "Recupera os dados a que tenham um valor menor ou igual que o passado")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/biggerthan/{tableEntity}/{value}")
    public ResponseEntity<Page<StatisticsModel>> getByBiggerThan(@PathVariable String tableEntity, float value, @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getByBiggerThan(tableEntity, value, page, size));
    }

    @Operation(summary = "Recupera statisticas trazendo todos que tenham um valor menor ou igual que o passado", description = "Recupera os dados a que tenham um valor maior ou igual que o passado")
    @ApiResponse(responseCode = "200", description = "Dado encontrado com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @ApiResponse(responseCode = "404", description = "Dado não encontrado")
    @GetMapping("/lesserthan/{tableEntity}/{value}")
    public ResponseEntity<Page<StatisticsModel>> getByLesserThan(@PathVariable String tableEntity, float value, @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getByLesserThan(tableEntity, value, page, size));
    }

    @Operation(summary = "Salva dados", description = "Salva dados")
    @ApiResponse(responseCode = "201", description = "Dados salvo com sucesso", content = @Content(schema = @Schema(implementation = StatisticsModel.class)))
    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody StatisticsDto statisticsDto, BindingResult result) {

        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(statisticsService.save(statisticsDto));

        } catch (Exception e) {
            return handleErrors(e);
        }
    }

    @Operation(summary = "Exclui dados pelo id" , description = "Exclui dados pelo id")
    @ApiResponse(responseCode = "204", description = "dados excluidos com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<StatisticsModel> optional = statisticsService.findById(id);
        return optional
                .map(aluno -> {
                    try {
                        statisticsService.deleteById(id);
                        return ResponseEntity.ok().build();
                    } catch (Exception e) {
                        return handleErrors(e);
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Altera um dado pelo Id" , description = "Altera um dado a partir do seu ID")
    @ApiResponse(responseCode = "200", description = "Dado alterado com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @Valid @RequestBody StatisticsDto statisticsDto, BindingResult result) {
        Optional<StatisticsModel> optional = statisticsService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            return result.hasErrors() ? ResponseEntity.unprocessableEntity().body(getErrors(result))
                    : ResponseEntity.status(HttpStatus.CREATED).body(statisticsService.save(copyProperties(statisticsDto, StatisticsModel.class)));

        } catch (Exception e) {
            return handleErrors(e);
        }
    }




}