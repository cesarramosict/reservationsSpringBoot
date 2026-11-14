-- Structure de la table pivot `reservations` (relation ManyToMany entre users et representations)
CREATE TABLE `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `representation_id` int(11) NOT NULL,
  `places` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index pour la table `reservations`
ALTER TABLE `reservations` ADD KEY `reservations_user_id` (`user_id`);
ALTER TABLE `reservations` ADD KEY `reservations_representation_id` (`representation_id`);

-- Contraintes pour la table `reservations` (clés étrangères vers users et representations)
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_user_id_fk_users_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_representation_id_fk_representations_id` FOREIGN KEY (`representation_id`) REFERENCES `representations` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;