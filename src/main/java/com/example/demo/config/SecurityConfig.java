@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

            // ✅ Allow auth APIs (REGISTER & LOGIN)
            .requestMatchers("/auth/**").permitAll()

            // ✅ Allow Swagger
            .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
            ).permitAll()

            // 🔒 Protect everything else
            .anyRequest().authenticated()
        )
        .httpBasic();   // OK for now

    return http.build();
}
