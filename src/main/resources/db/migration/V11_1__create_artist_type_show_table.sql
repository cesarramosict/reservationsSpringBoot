-- Structure de la table pivot `artist_type_show` (relation ManyToMany entre artist_type et shows)
CREATE TABLE `artist_type_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `artist_type_id` int(11) NOT NULL,
  `show_id` int(11) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index pour la table `artist_type_show`
ALTER TABLE `artist_type_show`
  ADD KEY `artist_type_show_show_id_fk_shows_id` (`show_id`),
  ADD KEY `artist_type_show_artist_type_id_fk_artist_type_id` (`artist_type_id`);

-- Contraintes pour la table `artist_type_show` (clés étrangères vers artist_type et shows)
ALTER TABLE `artist_type_show`
  ADD CONSTRAINT `artist_type_show_artist_type_id_fk_artist_type_id` FOREIGN KEY (`artist_type_id`) REFERENCES `artist_type` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT,
  ADD CONSTRAINT `artist_type_show_show_id_fk_shows_id` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;
