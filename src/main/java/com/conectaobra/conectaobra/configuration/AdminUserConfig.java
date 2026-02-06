//package com.conectaobra.conectaobra.configuration;
//
//import com.conectaobra.conectaobra.models.Role;
//import com.conectaobra.conectaobra.models.Usuario;
//import com.conectaobra.conectaobra.repositories.RoleRepository;
//import com.conectaobra.conectaobra.repositories.UsuarioRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@RequiredArgsConstructor
//public class AdminUserConfig implements CommandLineRunner {
//
//    private final RoleRepository roleRepository;
//    private final UsuarioRepository usuarioRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Role role = roleRepository.findById(1L).orElseThrow(() -> new UsernameNotFoundException("Role não encontrada!"));
//
//        var usuario = usuarioRepository.findByNome("admin");
//        usuario.ifPresentOrElse(
//                (user) -> System.out.println("admin já existe"),
//                () -> {
//                    usuarioRepository.save(new Usuario(null, "admin", passwordEncoder.encode("123"), role));
//                }
//        );
//    }
//}
