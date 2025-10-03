package mediaapp.com.just4you.Services.domain;

import mediaapp.com.just4you.DTOs.Create.AdicionarAvatar;
import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Repositories.AvatarRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AvatarService {

    @Autowired
    AvatarRepositorio avatarRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;



    public EntidadeAvatar novoAvatar(AdicionarAvatar dto){

        if(!avatarRepositorio.existsByUrl(dto.getUrl())){
            EntidadeAvatar entidadeAvatar = new EntidadeAvatar();
            entidadeAvatar.setDescricao(dto.getDescricao());
            entidadeAvatar.setUrl(dto.getUrl());
            return avatarRepositorio.save(entidadeAvatar);
        }
        throw new RuntimeException("Não deu"); // Em testes!
    }


    public EntidadeAvatar atualizarAvatar(EntidadeAvatar dto, Long id){
        if(avatarRepositorio.existsById(id)){
            EntidadeAvatar entidadeAvatar = avatarRepositorio.findById(id).get();
            Optional.ofNullable(dto.getUrl())
                    .filter(avatar -> !avatar.isBlank() && !entidadeAvatar.getUrl().equals(dto.getUrl()))
                    .ifPresent(entidadeAvatar::setUrl);
            Optional.ofNullable(dto.getDescricao())
                    .filter(descricao -> !descricao.isBlank() && !entidadeAvatar.getDescricao().equals(dto.getDescricao()))
                    .ifPresent(entidadeAvatar::setDescricao);

            return avatarRepositorio.save(entidadeAvatar);
        }
         throw new  RuntimeException("Não deu"); //TESTE

    }

    @Transactional
    public void deletarAvatar(Long id){
            EntidadeAvatar avatarDeletar = avatarRepositorio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Avatar com ID:" + id + " Não encontrado" )
                            );

            usuarioRepositorio.desassociarAvatar(avatarDeletar);
            usuarioRepositorio.deleteById(id);
    }

}
