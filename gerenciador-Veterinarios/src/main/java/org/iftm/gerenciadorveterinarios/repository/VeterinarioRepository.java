package org.iftm.gerenciadorveterinarios.repository;

import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {

   public List<Veterinario> findByNomeContains(String nome);


   @Query("SELECT v FROM Veterinario As v WHERE v.id = :id")
   public @NonNull Veterinario getById(@NonNull Integer id);

   @Query("SELECT v FROM Veterinario As v WHERE v.id = :id")
   public @NonNull Optional<Veterinario> findById(@NonNull Integer id);

   /*
      @Query("SELECT v FROM Veterinario AS v WHERE v.id = :id")
   public @NonNull Optional<Veterinario> findById(@NonNull Integer id);

   @Query("SELECT v FROM Veterinario AS v WHERE v.id = :id")
   public @NonNull Veterinario getById(@NonNull Integer id);


   //@Modifying
   //@Query("DELETE FROM Veterinario v WHERE v.id = :id")
   //public void deleteByIdOriginal(@NonNull Integer id);

   //@Modifying
	//@Query("DELETE FROM Veterinario obj WHERE " + "obj.email = :email")
	void deleteVeterinarioByEmail(String email);

	//@Query("SELECT DISTINCT obj FROM Veterinario obj WHERE " 			+ "obj.email = :email")
	Optional<Veterinario> findVeterinarioByEmail(String email);

   */
}

