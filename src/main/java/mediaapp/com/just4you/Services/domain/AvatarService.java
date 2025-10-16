package mediaapp.com.just4you.Services.domain;

import mediaapp.com.just4you.DTOs.Create.AdicionarAvatarDTO;
import mediaapp.com.just4you.DTOs.Put.EditarAvatar;
import mediaapp.com.just4you.DTOs.Response.AvatarDTO;
import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Exceptions.RecursoNaoEncontradoExcessao;
import mediaapp.com.just4you.Repositories.AvatarRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AvatarService {

    @Autowired
    AvatarRepositorio avatarRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;



    public AvatarDTO novoAvatar(AdicionarAvatarDTO dto){

        boolean entidadeExistente = avatarRepositorio.existsByUrl(dto.url());
        if(entidadeExistente){
            throw new RuntimeException("Já existe um avatar com a URL informada!");
        }

        EntidadeAvatar entidadeAvatar = new EntidadeAvatar();
        entidadeAvatar.setDescricao(dto.descricao());
        entidadeAvatar.setUrl(dto.url());

        EntidadeAvatar entidadeSalva = avatarRepositorio.save(entidadeAvatar);

        return  new AvatarDTO(entidadeSalva);
    }


    public AvatarDTO atualizarAvatar(EditarAvatar dto, Long id){
            EntidadeAvatar entidadeAvatar = avatarRepositorio.findById(id)
                            .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Avatar com ID: " + id + " não encontrado."));

            Optional.ofNullable(dto.url())
                    .filter(avatar -> !avatar.isBlank() && !entidadeAvatar.getUrl().equals(dto.descricao()))
                    .ifPresent(entidadeAvatar::setUrl);
            Optional.ofNullable(dto.descricao())
                    .filter(descricao -> !descricao.isBlank() && !entidadeAvatar.getDescricao().equals(dto.descricao()))
                    .ifPresent(entidadeAvatar::setDescricao);

            EntidadeAvatar entidadeSalva = avatarRepositorio.save(entidadeAvatar);
            return new AvatarDTO(entidadeSalva);

    }

    @Transactional
    public void deletarAvatar(Long id){
            EntidadeAvatar avatarDeletar = avatarRepositorio.findById(id)
                    .orElseThrow(() ->  new RecursoNaoEncontradoExcessao("Avatar com ID: " + id + " Não encontrado" )
                            );

            usuarioRepositorio.desassociarAvatar(avatarDeletar);
            avatarRepositorio.deleteById(id); // RETIRAR AVATAR
    }

    public List<AvatarDTO> listarAvatars(){
        List<EntidadeAvatar> avatares = avatarRepositorio.findAll();
        return avatares.stream()
                .map(AvatarDTO::new)
                .toList();
    }


    public Page<AvatarDTO> listarAvataresPaginacao(Pageable paginacao){
        Page<EntidadeAvatar> avatares = avatarRepositorio.findAll(paginacao);
        return avatares.map(AvatarDTO::new);
    }

    public AvatarDTO buscarAvatarPorId(Long id){
       return avatarRepositorio.findById(id)
                .map(AvatarDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoExcessao("Avatar com ID: " + id + " não encontrado."));
    }

}
