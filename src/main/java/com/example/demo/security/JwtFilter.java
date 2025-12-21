@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain chain)
        throws ServletException, IOException {

    String path = request.getRequestURI();

    // Skip JWT validation for public endpoints
    if (path.startsWith("/auth") ||
        path.startsWith("/label") ||
        path.startsWith("/recommendations") ||
        path.startsWith("/v3/api-docs") ||
        path.startsWith("/swagger-ui") ||
        path.equals("/error")) {

        chain.doFilter(request, response);
        return;
    }

    // Normal JWT validation for secured endpoints
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);

        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }
    }

    chain.doFilter(request, response);
}
