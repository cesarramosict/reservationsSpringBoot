-- Structure de la table `personal_access_tokens`
-- Adaptation : `token` en varchar(36) au lieu de uuid (type non disponible avant MariaDB 10.7)
CREATE TABLE `personal_access_tokens` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `token` varchar(36) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expires_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index pour la table `personal_access_tokens`
ALTER TABLE `personal_access_tokens`
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `user_id` (`user_id`);