package mediaapp.com.just4you.Services.domain;

import mediaapp.com.just4you.DTOs.Create.AdicionarAvatarDTO;
import mediaapp.com.just4you.DTOs.Response.AvatarDTO;
import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Repositories.AvatarRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
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

        EntidadeAvatar entidadeAvatar = avatarRepositorio.existsByUrl(dto.getUrl())
                .orElseThrow(() -> new RuntimeException("Já existe um AVATAR com essa URL"));

        entidadeAvatar.setDescricao(dto.getDescricao());
        entidadeAvatar.setUrl(dto.getUrl());

        EntidadeAvatar entidadeSalva = avatarRepositorio.save(entidadeAvatar);
        return  new AvatarDTO(entidadeAvatar);
    }


    public AvatarDTO atualizarAvatar(AdicionarAvatarDTO dto, Long id){
            EntidadeAvatar entidadeAvatar = avatarRepositorio.findById(id)
                            .orElseThrow(() -> new RuntimeException("Avatar com ID " + id + " não encontrado."));

            Optional.ofNullable(dto.getUrl())
                    .filter(avatar -> !avatar.isBlank() && !entidadeAvatar.getUrl().equals(dto.getUrl()))
                    .ifPresent(entidadeAvatar::setUrl);
            Optional.ofNullable(dto.getDescricao())
                    .filter(descricao -> !descricao.isBlank() && !entidadeAvatar.getDescricao().equals(dto.getDescricao()))
                    .ifPresent(entidadeAvatar::setDescricao);

            EntidadeAvatar entidadeSalva = avatarRepositorio.save(entidadeAvatar);
            return new AvatarDTO(entidadeSalva);

    }

    @Transactional
    public void deletarAvatar(Long id){
            EntidadeAvatar avatarDeletar = avatarRepositorio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Avatar com ID:" + id + " Não encontrado" )
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

    public AvatarDTO buscarAvatarPorId(Long id){
       return avatarRepositorio.findById(id)
                .map(AvatarDTO::new)
                .orElseThrow(() -> new RuntimeException("Avatar com ID " + id + " não encontrado."));
    }

}
