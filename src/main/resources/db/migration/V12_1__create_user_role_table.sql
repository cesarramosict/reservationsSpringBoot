-- Structure de la table pivot `user_role` (relation ManyToMany entre users et roles)
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index pour la table `user_role`
ALTER TABLE `user_role` ADD KEY `user_role_user_id` (`user_id`);
ALTER TABLE `user_role` ADD KEY `user_role_role_id` (`role_id`);

-- Contraintes pour la table `user_role` (clés étrangères vers users et roles)
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_user_id_fk_users_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_role_id_fk_roles_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;