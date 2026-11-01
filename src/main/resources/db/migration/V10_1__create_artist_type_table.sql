-- Structure de la table pivot `artist_type` (relation ManyToMany entre artists et types)
CREATE TABLE `artist_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artist_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index pour la table `artist_type`
ALTER TABLE `artist_type`
  ADD KEY `artist_type_artist_id_fk_artists_id` (`artist_id`);

ALTER TABLE `artist_type`
  ADD KEY `artist_type_type_id_fk_types_id` (`type_id`);

-- Contraintes pour la table `artist_type` (clés étrangères vers artists et types)
ALTER TABLE `artist_type`
  ADD CONSTRAINT `artist_type_artist_id_fk_artists_id` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE `artist_type`
  ADD CONSTRAINT `artist_type_type_id_fk_types_id` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;